#!/bin/bash
#git restore *.svg

# Optimize, rescale to 512 512 and center every svg in this folder.
# Using https://github.com/svg/svgo

svgo --folder . --multipass  --final-newline  --pretty --config svgo.config.js

head -n 1 *.svg

find . | grep '\.svg$' | while read line ; do
	VIEWBOX="$(cat "$line" | grep '^<svg' | tr '=' '\n'  | grep 'viewBox$' -A 1 | tail -n 1 | cut -d '"' -f 2)"
	if echo "x $VIEWBOX y" | grep -q ' 512 ' ; then
		echo "skipped: $line VIEWBOX: $VIEWBOX"
	else
		BIGGER="$(echo "$VIEWBOX" | tr ' ' '\n' | sort -n | tail -n 1)"
		echo "DOING: $line VIEWBOX: $VIEWBOX BIGGER: $BIGGER"
		SCALE="$(echo "512/$BIGGER" | bc -l | sed 's,^\.,0.,')"
		echo "SCALE: $SCALE"
		echo "$BIGGER * $SCALE" | bc -l
		OLDWIDTH="$(echo "${VIEWBOX}" | cut -d ' ' -f 3)"
		OLDHEIGHT="$(echo "${VIEWBOX}" | cut -d ' ' -f 4)"

		NEWWIDTH="$(echo "${OLDWIDTH} * ${SCALE}" | bc -l)"
		NEWHEIGHT="$(echo "${OLDHEIGHT} * ${SCALE}" | bc -l)"

		TRANSX="$(echo "(512.0 - ${NEWWIDTH}) / 2.0" | bc -l)"
		TRANSY="$(echo "(512.0 - ${NEWHEIGHT}) / 2.0" | bc -l)"

		sed -i "2i<g transform=\"translate(${TRANSX} ${TRANSY})\"><g transform=\"scale(${SCALE})\">" "$line"
		sed -i 's,</svg>,</g></g></svg>,g' "$line"
		sed -i 's,viewBox="[^"]*",viewBox="0 0 512 512",g' "$line"
	fi
done

svgo --folder . --multipass  --final-newline  --pretty --config svgo.config.js

#pushd ..
#ant dist
#popd
#
#pushd ../../bauernhof
#ant build
#./run.sh
#popd

